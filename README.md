## Riko

Riko is a third-party Java library for accessing Upstox API.

*Minimum JDK required - __JDK 9__*

#### Riko internally depends on

- Retrofit2
- OkHttp3
- Google Gson
- Google Guava
- Log4J 2
- Junit 5

### Riko's quirks

###### Using Riko

Refer the unit tests to understand how you can use Riko in your application.

###### ServiceGenerator::rebuildWithUrl(HttpUrl url)

The `ServiceGenerator` has a `rebuildWithUrl(HttpUrl url)` function that allows for easy unit testing of Riko, 
and hence, only usable when developing Riko. Ignore this function, as long as you only want to use Riko as dependency.

###### Constants

Have declared some constants under the `com.github.rishabh9.riko.upstox.common.constants` package that you can make use
 of.

###### NumberString

The Upstox API that fetches the current positions for the day, returns certain numeric data as empty String if no value
exists for the same. In a dynamic typed language this may work, but in a strongly typed language like Java, this causes 
a lot of pain. To support such data, we have the NumberString class and the support (de)serializers.

###### Web socket

You need to connect using web socket to receive various updates from Upstox. The internal web socket implementation 
(`MessageListener`) here uses the Java 9 Flow API to publish the incoming messages to the interested 
subscribers (`MessageSubscriber`). Example:

    public class TextMessageSubscriber implements MessageSubscriber {...}
    public class BinaryMessageSubscriber implements MessageSubscriber {...}
    public class ConnectionStatusSubscriber implements MessageSubscriber {...}
    
    ...

    List<MessageSubscriber> prepareAllTypesOfMessageSubscribers() {
        List<MessageSubscriber> subscribers = new ArrayList<>();
        subscribers.add(new TextMessageSubscriber());
        subscribers.add(new BinaryMessageSubscriber());
        subscribers.add(new ConnectionStatusSubscriber());
        return subscribers;
    }
    
    ...
    
    // Prepare the message subscribers
    List<MessageSubscriber> subscribers = prepareAllTypesOfMessageSubscribers();
    
    // Connecting to the web socket
    WebSocketService service = new WebSocketService(accessToken, apiCredentials);
    WrappedWebSocket socket = service.connect(subscribers);

###### System properties

The following system properties can be used to configure the library:

|System property|Description|Default value|
|:---:|:---:|:---:|
|`riko.read.timeout`|Number of seconds for read to timeout|10|
|`riko.write.timeout`|Number of seconds for write to timeout|10|
|`riko.connect.timeout`|Number of seconds for connect to timeout|10|
|`riko.server.url`|The API URL to use|`api.upstox.com`|
|`riko.server.port`|The port to use|`80` or `443` based on the scheme specified|
|`riko.server.scheme`|The scheme to use|`https`|
|`riko.ws.server.url`|The URL to use for websocket|`ws-api.upstox.com`|
|`riko.ws.server.port`|The port to use for websocket|`80` or `443` based on the scheme specified|
|`riko.ws.server.scheme`|The scheme to use|`https`|


#### Including Riko in your project

###### Maven

    <dependency>
        <groupId>com.github.rishabh9</groupId>
        <artifactId>riko</artifactId>
        <version>1.0.0</version>
    </dependency>

###### Gradle

    dependencies {
        implementation 'com.github.rishabh9:riko:1.0.0'
    }

#### Progaurd

Riko is built using [Retrofit](http://square.github.io/retrofit/), 
and Retrofit internally uses [OkHttp](https://github.com/square/okhttp) 
and [Okio](https://github.com/square/okio).
Please refer their respective documentation for Progaurd configurations:

- [Retrofit](https://github.com/square/retrofit#proguard)
- [OkHttp](https://github.com/square/okhttp#proguard)
- [Okio](https://github.com/square/okio#proguard)
