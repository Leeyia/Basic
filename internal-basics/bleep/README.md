### BluetoothKit
Android Bluetooth Low Energy.
- UI Thread invoke bluetooth API
- Java 8 lambda-friendly API
- Easy replace bluetooth frame

**示例:**  
initialization：
```
Configurations configures = Configurations.newBuilder()
    .serviceUuid(Configurations.UUID.SERVICE_UUID)
    .characterUuid(Configurations.UUID.CHARACTER_UUID)
    .operation(new SystemOperation())
    .timeout(6000ms)
    .setSplit(false)
    .build();
BluetoothKit.init(this).configurations(configures);
```

connection：
```
BluetoothKit
        .get()
        .connect(device)
        .done(device -> {
            //success do somethings
        })
        .fail((device, message) -> {
            //fail do somethings
        })
        .enqueue();
```
notification：
```
BluetoothKit
        .get()
        .notification(device)
        .done(device -> {
            //success do somethings
        })
        .fail((device, message) -> {
            //fail do somethings
        })
        .enqueue();
```

write：
```
BluetoothKit
        .get()
        .write(device, bytes)
        .done(device -> {
            //success do somethings
        })
        .fail((device, message) -> {
            //fail do somethings
        })
        .enqueue();
```

### download
```
dependencies {
    implementation 'com.dintech.api:bleep:1.1.6'
}
```

### feature
1.2.0  
/-[] 重试机制
[ ] 连接超时
[x] 代码优化