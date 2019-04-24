## BluetoothKit
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

## download
```
dependencies {
    implementation 'com.dintech.api:bleep:1.1.6'
}
```

## feature
1.2.0  
[ ] 连接超时    
[x] 代码优化

## License
```
Copyright [yyyy] [name of copyright owner]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```