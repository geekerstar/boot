package com.geekerstar.function.util;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author geekerstar
 * @date 2023/7/12 10:28
 * https://www.cnblogs.com/ioufev/p/10831289.html
 * https://www.cnblogs.com/kikyoqiang/p/14238436.html
 */
@Slf4j
public class ModbusUtil {

    static ModbusFactory modbusFactory;

    static {
        modbusFactory = new ModbusFactory();
    }

    /**
     * 获取tcpMaster
     *
     * @return
     * @throws ModbusInitException
     */
    public static ModbusMaster getMaster() throws ModbusInitException {
        IpParameters params = new IpParameters();
        params.setHost("localhost");
        params.setPort(502);

        // modbusFactory.createRtuMaster(wapper); //RTU 协议
        // modbusFactory.createUdpMaster(params);//UDP 协议
        // modbusFactory.createAsciiMaster(wrapper);//ASCII 协议
        ModbusMaster tcpMaster = modbusFactory.createTcpMaster(params, false);
        tcpMaster.init();

        return tcpMaster;
    }

    /**
     * 读取[01 Coil Status 0x]类型 开关数据
     *
     * @param slaveId slaveId
     * @param offset 位置
     * @return 读取值
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static Boolean readCoilStatus(int slaveId, int offset)
        throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        BaseLocator<Boolean> loc = BaseLocator.coilStatus(slaveId, offset);
        return getMaster().getValue(loc);
    }

    /**
     * 读取[02 Input Status 1x]类型 开关数据
     *
     * @param slaveId
     * @param offset
     * @return
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static Boolean readInputStatus(int slaveId, int offset)
        throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        BaseLocator<Boolean> loc = BaseLocator.inputStatus(slaveId, offset);
        return getMaster().getValue(loc);
    }

    /**
     * 读取[03 Holding Register类型 2x]模拟量数据
     *
     * @param slaveId slave Id
     * @param offset 位置
     * @param dataType 数据类型,来自com.serotonin.modbus4j.code.DataType
     * @return
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static Number readHoldingRegister(int slaveId, int offset, int dataType)
        throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        BaseLocator<Number> loc = BaseLocator.holdingRegister(slaveId, offset, dataType);
        return getMaster().getValue(loc);
    }

    /**
     * 读取[04 Input Registers 3x]类型 模拟量数据
     *
     * @param slaveId slaveId
     * @param offset 位置
     * @param dataType 数据类型,来自com.serotonin.modbus4j.code.DataType
     * @return 返回结果
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static Number readInputRegisters(int slaveId, int offset, int dataType)
        throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        BaseLocator<Number> loc = BaseLocator.inputRegister(slaveId, offset, dataType);
        return getMaster().getValue(loc);
    }

    /**
     * 批量读取使用方法
     *
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static void batchRead() throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        BatchRead<Integer> batch = new BatchRead<>();
        batch.addLocator(0, BaseLocator.holdingRegister(1, 1, DataType.FOUR_BYTE_FLOAT));
        batch.addLocator(1, BaseLocator.inputStatus(1, 0));
        ModbusMaster master = getMaster();
        batch.setContiguousRequests(false);
        BatchResults<Integer> results = master.send(batch);
        System.out.println(results.getValue(0));
        System.out.println(results.getValue(1));
    }

    /**
     * 写 [01 Coil Status(0x)]写一个 function
     *
     * @param slaveId     slave的ID
     * @param writeOffset 位置
     * @param writeValue  值
     * @return 是否写入成功
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    public static boolean writeCoil(int slaveId, int writeOffset, boolean writeValue)
        throws ModbusTransportException, ModbusInitException {
        // 获取master
        ModbusMaster tcpMaster = getMaster();
        // 创建请求
        WriteCoilRequest request = new WriteCoilRequest(slaveId, writeOffset, writeValue);
        // 发送请求并获取响应对象
        WriteCoilResponse response = (WriteCoilResponse) tcpMaster.send(request);
        return !response.isException();
    }

    /**
     * 写[01 Coil Status(0x)] 写多个 function ID = 15
     *
     * @param slaveId     slaveId
     * @param startOffset 开始位置
     * @param writeData   写入的数据
     * @return 是否写入成功
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    public static boolean writeCoilBatch(int slaveId, int startOffset, boolean[] writeData)
        throws ModbusTransportException, ModbusInitException {
        // 获取master
        ModbusMaster tcpMaster = getMaster();
        // 创建请求
        WriteCoilsRequest request = new WriteCoilsRequest(slaveId, startOffset, writeData);
        // 发送请求并获取响应对象
        WriteCoilsResponse response = (WriteCoilsResponse) tcpMaster.send(request);
        return !response.isException();

    }

    /***
     * 写[03 Holding Register(4x)] 写一个 function ID = 6
     *
     * @param slaveId
     * @param writeOffset
     * @param writeValue
     * @return
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    public static boolean writeRegister(int slaveId, int writeOffset, short writeValue)
        throws ModbusTransportException, ModbusInitException {
        // 获取master
        ModbusMaster tcpMaster = getMaster();
        // 创建请求对象
        WriteRegisterRequest request = new WriteRegisterRequest(slaveId, writeOffset, writeValue);
        WriteRegisterResponse response = (WriteRegisterResponse) tcpMaster.send(request);
        if (response.isException()) {
            log.error(response.getExceptionMessage());
            return false;
        } else {
            return true;
        }

    }

    /**
     * 写入[03 Holding Register(4x)]写多个 function ID=16
     *
     * @param slaveId     modbus的slaveID
     * @param startOffset 起始位置偏移量值
     * @param writeData       写入的数据
     * @return 返回是否写入成功
     * @throws ModbusTransportException
     * @throws ModbusInitException
     */
    public static boolean writeRegisterBatch(int slaveId, int startOffset, short[] writeData)
        throws ModbusTransportException, ModbusInitException {
        // 获取master
        ModbusMaster tcpMaster = getMaster();
        // 创建请求对象
        WriteRegistersRequest request = new WriteRegistersRequest(slaveId, startOffset, writeData);
        // 发送请求并获取响应对象
        ModbusResponse response = tcpMaster.send(request);
        if (response.isException()) {
            log.error(response.getExceptionMessage());
            return false;
        } else {
            return true;
        }
    }

    /**
     * 写入数字类型的模拟量（如:写入Float类型的模拟量、Double类型模拟量、整数类型Short、Integer、Long）
     *
     * @param slaveId
     * @param offset
     * @param value         写入值,Number的子类,例如写入Float浮点类型,Double双精度类型,以及整型short,int,long
     * @param dataType ,com.serotonin.modbus4j.code.DataType
     * @throws ModbusTransportException
     * @throws ErrorResponseException
     * @throws ModbusInitException
     */
    public static void writeHoldingRegister(int slaveId, int offset, Number value, int dataType)
        throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        // 获取master
        ModbusMaster tcpMaster = getMaster();
        // 类型
        BaseLocator<Number> locator = BaseLocator.holdingRegister(slaveId, offset, dataType);
        tcpMaster.setValue(locator, value);
    }

    public static void main(String[] args) {
        try {
            // 01测试
            Boolean v010 = readCoilStatus(1, 0);
            Boolean v011 = readCoilStatus(1, 1);
            Boolean v016 = readCoilStatus(1, 6);
            System.out.println("v010:" + v010);
            System.out.println("v011:" + v011);
            System.out.println("v016:" + v016);

            // 02测试
            Boolean v020 = readInputStatus(1, 0);
            Boolean v021 = readInputStatus(1, 1);
            Boolean v022 = readInputStatus(1, 2);
            System.out.println("v020:" + v020);
            System.out.println("v021:" + v021);
            System.out.println("v022:" + v022);

            // 03测试
            Number v031 = readHoldingRegister(1, 1, DataType.FOUR_BYTE_FLOAT);
            Number v033 = readHoldingRegister(1, 3, DataType.FOUR_BYTE_FLOAT);
            System.out.println("v031:" + v031);
            System.out.println("v033:" + v033);

            // 04测试
            Number v040 = readInputRegisters(1, 0, DataType.FOUR_BYTE_FLOAT);
            Number v042 = readInputRegisters(1, 2, DataType.FOUR_BYTE_FLOAT);
            System.out.println("v040:" + v040);
            System.out.println("v042:" + v042);
            // 批量读取
            batchRead();

            // 写入测试
            // 测试01
			boolean t01 = writeCoil(1, 1, true);
			System.out.println("T01:" + t01);

            // 测试02
			boolean t02 = writeCoilBatch(1, 0, new boolean[] { false, false, true });
			System.out.println("T02:" + t02);

            // 测试03
			short v = -3;
			boolean t03 = writeRegister(1, 0, v);
			System.out.println("T03:" + t03);
            // 测试04
			boolean t04 = writeRegisterBatch(1, 5, new short[] { -3, 3, 9 });
			System.out.println("t04:" + t04);
            // 写模拟量
            writeHoldingRegister(1,0, 10.1f, DataType.FOUR_BYTE_FLOAT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
