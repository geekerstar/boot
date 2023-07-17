package com.geekerstar.function.util;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.identity.UsernameProvider;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author geekerstar
 * @date 2023/7/13 11:27
 *
 * Modbus Poll/Slave 模拟器使用教程
 * https://blog.csdn.net/qq_35029061/article/details/125865898
 *
 * 【工具使用】Modbus Poll软件使用详解
 * https://blog.csdn.net/u012749085/article/details/125270869
 *
 * java代码配置连接OPCUA
 * https://www.jianshu.com/p/c3cb55221908
 */
@Slf4j
public class OpcUaUtil {
    private final static String END_POINT_URL = "opc.tcp://127.0.0.1:49320";

    public static void main(String[] args) {
        try {
            //创建OPC UA客户端
            OpcUaClient opcUaClient = createClient();
            //开启连接
            opcUaClient.connect().get();
            // 订阅消息
//            subscribe(opcUaClient);
            // 写入
            writeValue(opcUaClient);
            // 读取
            readValue(opcUaClient);
            // 关闭连接
            opcUaClient.disconnect().get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建OPC UA客户端
     *
     * @return
     * @throws Exception
     */
    private static OpcUaClient createClient() throws Exception {
        Path securityTempDir = Paths.get(System.getProperty("java.io.tmpdir"), "security");
        Files.createDirectories(securityTempDir);
        if (!Files.exists(securityTempDir)) {
            throw new Exception("unable to create security dir: " + securityTempDir);
        }
        return OpcUaClient.create(END_POINT_URL,
            endpoints ->
                endpoints.stream()
                    .filter(e -> e.getSecurityPolicyUri().equals(SecurityPolicy.None.getUri()))
                    .findFirst(),
            configBuilder ->
                configBuilder
                    .setApplicationName(LocalizedText.english(""))
                    // ns=2:s=test.device1.param1
                    .setApplicationUri("")
                    //访问方式
//                    .setIdentityProvider(new AnonymousProvider())
                    .setIdentityProvider(new UsernameProvider("admin", "admin"))
                    .setRequestTimeout(UInteger.valueOf(5000))
                    .build()
        );
    }

    private static void subscribe(OpcUaClient client) throws Exception {
        //创建发布间隔1000ms的订阅对象
        client.getSubscriptionManager()
            .createSubscription(1000.0)
            .thenAccept(t -> {
                //节点ns=2;s=test.device1.Ramp1
                NodeId nodeId = new NodeId(2, "test.device1.Ramp1");
                ReadValueId readValueId = new ReadValueId(nodeId, AttributeId.Value.uid(), null, null);
                //创建监控的参数
                MonitoringParameters parameters = new MonitoringParameters(UInteger.valueOf(1), 1000.0, null, UInteger.valueOf(10), true);
                //创建监控项请求
                //该请求最后用于创建订阅。
                MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(readValueId, MonitoringMode.Reporting, parameters);
                List<MonitoredItemCreateRequest> requests = new ArrayList<>();
                requests.add(request);
                //创建监控项，并且注册变量值改变时候的回调函数。
                t.createMonitoredItems(
                    TimestampsToReturn.Both,
                    requests,
                    (item, id) -> item.setValueConsumer((it, val) -> log.info("订阅nodeId：{},订阅value：{}", it.getReadValueId().getNodeId(), val.getValue().getValue()))
                );
            }).get();

        //持续订阅
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void readValue(OpcUaClient client) {
        try {
            NodeId nodeId = new NodeId(2, "test.device1.param1");
            DataValue value = client.readValue(0.0, TimestampsToReturn.Both, nodeId).get();
            log.info("读取：{}", value.getValue().getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeValue(OpcUaClient client) {
        try {
            //创建变量节点 test.device1.Ramp1
            NodeId nodeId = new NodeId(2, "test.device1.param1");
            boolean value = false;
            //创建Variant对象和DataValue对象
            Variant v = new Variant(value);
            DataValue dataValue = new DataValue(v, null, null);
            StatusCode statusCode = client.writeValue(nodeId, dataValue).get();
            log.info("写入值：{},写入响应：{}", statusCode.isGood(), statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
