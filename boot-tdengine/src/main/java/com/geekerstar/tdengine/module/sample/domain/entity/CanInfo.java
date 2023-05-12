package com.geekerstar.tdengine.module.sample.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * can报文信息(CanInfo)表实体类
 *
 * @author geekerstar
 * @since 2023-05-12 10:51:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CanInfo", description = "can报文信息")
public class CanInfo implements Serializable {

    private static final long serialVersionUID = -57794558115593755L;

    @ApiModelProperty(value = "${column.comment}")
    private String 国标数据时间;

    @ApiModelProperty(value = "${column.comment}")
    private String 车辆状态;

    @ApiModelProperty(value = "${column.comment}")
    private String 充电状态;

    @ApiModelProperty(value = "${column.comment}")
    private String 运行模式;

    @ApiModelProperty(value = "${column.comment}")
    private String 车速;

    @ApiModelProperty(value = "${column.comment}")
    private String 累计里程;

    @ApiModelProperty(value = "${column.comment}")
    private String 总电压;

    @ApiModelProperty(value = "${column.comment}")
    private String 总电流;

    @ApiModelProperty(value = "${column.comment}")
    private String soc;

    @ApiModelProperty(value = "${column.comment}")
    private String dc状态;

    @ApiModelProperty(value = "${column.comment}")
    private String 驱动力;

    @ApiModelProperty(value = "${column.comment}")
    private String 制动力;

    @ApiModelProperty(value = "${column.comment}")
    private String 档位;

    @ApiModelProperty(value = "${column.comment}")
    private String 绝缘电阻;

    @ApiModelProperty(value = "${column.comment}")
    private String 加速踏板行程值;

    @ApiModelProperty(value = "${column.comment}")
    private String 制动踏板状态;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 驱动电机个数;

    @ApiModelProperty(value = "${column.comment}")
    private String 驱动电机详情;

    @ApiModelProperty(value = "${column.comment}")
    private String 定位状态;

    @ApiModelProperty(value = "${column.comment}")
    private String 经度;

    @ApiModelProperty(value = "${column.comment}")
    private String 纬度;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 最高电压电池子系统代号;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 最高电压电池单体代号;

    @ApiModelProperty(value = "${column.comment}")
    private String 电池单体电压最高值;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 最低电压电池子系统代号;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 最低电压电池单体代号;

    @ApiModelProperty(value = "${column.comment}")
    private String 电池单体电压最低值;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 最高温度子系统代号;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 最高温度探针序号;

    @ApiModelProperty(value = "${column.comment}")
    private String 最高温度值;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 最低温子系统号;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 最低温度探针序号;

    @ApiModelProperty(value = "${column.comment}")
    private String 最低温度值;

    @ApiModelProperty(value = "${column.comment}")
    private String 最高报警等级;

    @ApiModelProperty(value = "${column.comment}")
    private String 通用报警标志;

    @ApiModelProperty(value = "${column.comment}")
    private String 温度差异报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 电池高温报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 车载储能装置类型过压报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 车载储能装置类型欠压报警;

    @ApiModelProperty(value = "${column.comment}")
    private String soc低报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 单体电池过压报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 单体电池欠压报警;

    @ApiModelProperty(value = "${column.comment}")
    private String soc过高报警;

    @ApiModelProperty(value = "${column.comment}")
    private String soc跳变报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 可充电储能系统不匹配报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 电池单体一致性差报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 绝缘报警;

    @ApiModelProperty(value = "${column.comment}")
    private String dcdc温度报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 制动系统报警;

    @ApiModelProperty(value = "${column.comment}")
    private String dcdc状态报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 驱动电机控制器温度报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 高压互锁状态报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 驱动电机温度报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 车载储能装置类型过充;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 可充电储能装置故障总数;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 驱动电机故障总数;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 发动机故障总数;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 其他故障总数;

    @ApiModelProperty(value = "${column.comment}")
    private String 可充电储能子系统个数_电压;

    @ApiModelProperty(value = "${column.comment}")
    private String 可充电储能子系统个数_温度;

    @ApiModelProperty(value = "${column.comment}")
    private String 累计充电量;

    @ApiModelProperty(value = "${column.comment}")
    private String 瞬时消耗率;

    @ApiModelProperty(value = "${column.comment}")
    private String dcac报警等级;

    @ApiModelProperty(value = "${column.comment}")
    private String aebs状态;

    @ApiModelProperty(value = "${column.comment}")
    private String 碰撞等级;

    @ApiModelProperty(value = "${column.comment}")
    private String aebs跟踪目标障碍物类型;

    @ApiModelProperty(value = "${column.comment}")
    private String 左偏离报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 右偏离报警;

    @ApiModelProperty(value = "${column.comment}")
    private String 方向盘角度;

    @ApiModelProperty(value = "${column.comment}")
    private String 方向盘转向计数器;

    @ApiModelProperty(value = "${column.comment}")
    private String 方向盘角度传感器类型;

    @ApiModelProperty(value = "${column.comment}")
    private String z轴旋转率;

    @ApiModelProperty(value = "${column.comment}")
    private String y轴横向加速度;

    @ApiModelProperty(value = "${column.comment}")
    private String x轴纵向加速度;

    @ApiModelProperty(value = "${column.comment}")
    private String 闭眼一级;

    @ApiModelProperty(value = "${column.comment}")
    private String 闭眼二级;

    @ApiModelProperty(value = "${column.comment}")
    private String 左顾右盼;

    @ApiModelProperty(value = "${column.comment}")
    private String 打哈欠;

    @ApiModelProperty(value = "${column.comment}")
    private String 打电话;

    @ApiModelProperty(value = "${column.comment}")
    private String 抽烟;

    @ApiModelProperty(value = "${column.comment}")
    private String 离岗;

    @ApiModelProperty(value = "${column.comment}")
    private String 低头;

    @ApiModelProperty(value = "${column.comment}")
    private String 抬头;

    @ApiModelProperty(value = "${column.comment}")
    private String 驾驶员变更;

    @ApiModelProperty(value = "${column.comment}")
    private String 摄像头遮挡;

    @ApiModelProperty(value = "${column.comment}")
    private String 墨镜遮挡;

    @ApiModelProperty(value = "${column.comment}")
    private String 双手脱离方向盘;

    @ApiModelProperty(value = "${column.comment}")
    private String 单手脱离方向盘;

    @ApiModelProperty(value = "${column.comment}")
    private String 长时间驾驶;

    @ApiModelProperty(value = "${column.comment}")
    private String 过道超员;

    @ApiModelProperty(value = "${column.comment}")
    private String 身份不符;

    @ApiModelProperty(value = "${column.comment}")
    private String 未系安全带;

    @ApiModelProperty(value = "${column.comment}")
    private String 喝水;

    @ApiModelProperty(value = "${column.comment}")
    private String 玩手机;

    @ApiModelProperty(value = "${column.comment}")
    private String 身份识别状态;

    @ApiModelProperty(value = "${column.comment}")
    private String 人脸抓拍;

    @ApiModelProperty(value = "${column.comment}")
    private String 检测到司机;

    @ApiModelProperty(value = "${column.comment}")
    private String dms车速;

    @ApiModelProperty(value = "${column.comment}")
    private String adas声音类型;

    @ApiModelProperty(value = "${column.comment}")
    private String adas日夜状态;

    @ApiModelProperty(value = "${column.comment}")
    private String adas停车状态;

    @ApiModelProperty(value = "${column.comment}")
    private String adas车距预警;

    @ApiModelProperty(value = "${column.comment}")
    private String adas碰撞时间;

    @ApiModelProperty(value = "${column.comment}")
    private String adas故障;

    @ApiModelProperty(value = "${column.comment}")
    private String 车道偏离禁用;

    @ApiModelProperty(value = "${column.comment}")
    private String 车道偏离左;

    @ApiModelProperty(value = "${column.comment}")
    private String 车道偏离右;

    @ApiModelProperty(value = "${column.comment}")
    private String 碰撞预警;

    @ApiModelProperty(value = "${column.comment}")
    private String 行人碰撞;

    @ApiModelProperty(value = "${column.comment}")
    private String 行人区域;

    @ApiModelProperty(value = "${column.comment}")
    private String 超速使能;

    @ApiModelProperty(value = "${column.comment}")
    private String 超速等级;

    @ApiModelProperty(value = "${column.comment}")
    private String 车距等级;

    @ApiModelProperty(value = "${column.comment}")
    private String 胎压监测模块安装轮胎位置;

    @ApiModelProperty(value = "${column.comment}")
    private String 车型定义;

    @ApiModelProperty(value = "${column.comment}")
    private String 润滑系统状态;

    @ApiModelProperty(value = "${column.comment}")
    private String 制动钳左前传感器温度;

    @ApiModelProperty(value = "${column.comment}")
    private String 制动钳右前传感器温度;

    @ApiModelProperty(value = "${column.comment}")
    private String 制动钳左中传感器温度;

    @ApiModelProperty(value = "${column.comment}")
    private String 制动钳右中传感器温度;

    @ApiModelProperty(value = "${column.comment}")
    private String 制动钳左后传感器温度;

    @ApiModelProperty(value = "${column.comment}")
    private String 制动钳右后传感器温度;

    @ApiModelProperty(value = "${column.comment}")
    private String 制动钳本地告警温度设置;

    @ApiModelProperty(value = "${column.comment}")
    private String 后舱门状态;

    @ApiModelProperty(value = "${column.comment}")
    private String 手刹开关;

    @ApiModelProperty(value = "${column.comment}")
    private String 干燥器排气压力开关;

    @ApiModelProperty(value = "${column.comment}")
    private String 一号气压报警输入;

    @ApiModelProperty(value = "${column.comment}")
    private String 二号气压报警输入;

    @ApiModelProperty(value = "${column.comment}")
    private String 前乘客门开关信号;

    @ApiModelProperty(value = "${column.comment}")
    private String 后乘客门开关信号;

    @ApiModelProperty(value = "${column.comment}")
    private String 充电口舱门信号;

    @ApiModelProperty(value = "${column.comment}")
    private String 碰撞信号;

    @ApiModelProperty(value = "${column.comment}")
    private String 应急门开关信号;

    @ApiModelProperty(value = "${column.comment}")
    private String 低压电瓶电压;

    @ApiModelProperty(value = "${column.comment}")
    private String 一号气瓶压力;

    @ApiModelProperty(value = "${column.comment}")
    private String 二号气瓶压力;

    @ApiModelProperty(value = "${column.comment}")
    private String 转向灯信号;

    @ApiModelProperty(value = "${column.comment}")
    private String 危险指示灯信号;

    @ApiModelProperty(value = "${column.comment}")
    private String 远近光灯信号;

    @ApiModelProperty(value = "${column.comment}")
    private String 雨刮信号;

    @ApiModelProperty(value = "${column.comment}")
    private String 机舱烟雾报警1号传感器除霜器;

    @ApiModelProperty(value = "${column.comment}")
    private String 机舱烟雾报警2号传感器顶前部电池舱;

    @ApiModelProperty(value = "${column.comment}")
    private String 机舱烟雾报警3号传感器后部顶电池舱;

    @ApiModelProperty(value = "${column.comment}")
    private String 机舱烟雾报警4号传感器后电器舱;

    @ApiModelProperty(value = "${column.comment}")
    private String 机舱烟雾报警5号传感器后电池舱;

    @ApiModelProperty(value = "${column.comment}")
    private String 机舱烟雾报警6号传感器;

    @ApiModelProperty(value = "${column.comment}")
    private String 机舱烟雾报警7号传感器;

    @ApiModelProperty(value = "${column.comment}")
    private String 机舱烟雾报警8号传感器;

    @ApiModelProperty(value = "${column.comment}")
    private String 机舱烟雾报警9号传感器;

    @ApiModelProperty(value = "${column.comment}")
    private String 机舱烟雾报警10号传感器;

    @ApiModelProperty(value = "${column.comment}")
    private String soh;

    @ApiModelProperty(value = "${column.comment}")
    private String 当前最高故障等级;

    @ApiModelProperty(value = "${column.comment}")
    private String 电芯温度最大值;

    @ApiModelProperty(value = "${column.comment}")
    private String 电芯温度最小值;

    @ApiModelProperty(value = "${column.comment}")
    private String 电芯温度平均值;

    @ApiModelProperty(value = "${column.comment}")
    private String 电芯电压最大值;

    @ApiModelProperty(value = "${column.comment}")
    private String 电芯电压最小值;

    @ApiModelProperty(value = "${column.comment}")
    private String 电芯电压平均值;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 整车系统故障码;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 转向泵故障码;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 轮胎个数;

    @ApiModelProperty(value = "${column.comment}")
    private String 轮胎详情;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 电池箱个数;

    @ApiModelProperty(value = "${column.comment}")
    private String 电池箱详情;

    @ApiModelProperty(value = "${column.comment}")
    private String 全速上传倒计时;

    @ApiModelProperty(value = "${column.comment}")
    private Long msgId;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 车辆编号;

    @ApiModelProperty(value = "${column.comment}")
    private String 网关编号;

    @ApiModelProperty(value = "${column.comment}")
    private Integer 租户ID;

    @ApiModelProperty(value = "${column.comment}")
    private String 网关时间;
}

