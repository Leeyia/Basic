## Bluetooth
- scanPeriodMillis
- waitTimeMillis
- filter






## Develop-things bluetooth
蓝牙扫描/过滤/连接/写入/读取

## Android 6.0 and runtime permissions
根据Android平台，您需要对已完成的应用程序授予不同的权限。建议将来执行Android运行时权限模型。
为了可靠地检测信标，必须满足以下条件：
授予蓝牙权限（ANDROID.PrimeSist.Bluetooth和Android）。这是自动完成的，如果您使用的是IDEMOTE SDK。
在AndroidManifest.xml中声明了BeaCon服务。这是自动完成的，如果您使用的是IDEMOTE SDK。
如果运行在Android M或更高版本上，则必须打开位置服务。
如果运行在Android或以后和您的应用程序的目标是SDK＜23（M）、任何地点（access_coarse_location许可或access_fine_location）必须授予背景信标检测。
如果运行在Android或以后和您的应用程序的目标是SDK > = 23（M）、任何地点（access_coarse_location许可或access_fine_location必须授予。
听起来很难？别担心。SDK有时会在设备日志中发出警告，指定丢失的内容。您可以使用StaseRealStutsSeCKECK检查方法来确定信标检测不满足哪些要求。使用在您的活动systemrequirementschecker # checkwithdefaultdialogs方法方便地要求所有的权限和权利。这都是由SDK处理的，如果你想尽快起床和运行，应该会有很大的帮助。

## Android 7.0 and BLE scan restrictions
自Nougat，每一个应用程序都允许启动/停止BLE扫描，每30s最多扫描5次。由于这个限制，
如果您将**监视周期设置为小于6s（例如‘1000毫秒扫描+0MS等待’）， 我们的SDK将自动将扫描时间设置为“6000毫秒”。
你仍然应该正确地进入/退出结果。我们强烈建议您根据Android N.验证您的监控周期。
此限制不影响测距次-当在前台运行时，您的测距将根据您宣布的周期提供恒定的结果。唯一的变化是扫描在整个测距过程中不断地运行，这将耗尽。