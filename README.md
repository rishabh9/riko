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

###### NumberString

The Upstox API that fetches the current positions for the day, returns certain numeric data as empty String if no value
exists for the same. In a dynamic typed language this may work, but in a strongly typed language like Java, this causes 
a lot of pain. To support such data, we have the NumberString class and the support (de)serializers.

###### Web socket

You need to connect using web socket to receive various updates from Upstox. The web socket implementation 
(`WebSocketListener`) here uses the Java9 Flow API to publish the incoming messages to the interested 
subscribers (`WebSocketMessageSubscriber`).

    // Prepare the message subscribers
    List<WebSocketMessageSubscriber> subscribers = prepareAllTypesOfMessageSubscribers();
    
    // Connecting to the web socket
    WebSocketService service = new WebSocketService(accessToken, apiCredentials);
    WrappedWebSocket socket = service.connect(subscribers);

#### Riko Vanilla

The build from `vanilla` branch is called as `riko-vanilla`. It does not use any of the Retrofit2 call adapters.
In case you are turned off by the Java8's `CompletableFuture` interface, `riko-vanilla` is your only hope. ^_^

#### Including Riko in your project

###### Maven

    <dependency>
        <groupId>com.github.rishabh9</groupId>
        <artifactId>riko</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>

in case of `riko-vanilla`

    <dependency>
        <groupId>com.github.rishabh9</groupId>
        <artifactId>riko-vanilla</artifactId>
        <version>1.0.0-SNAPSHOT</version>
    </dependency>

###### Gradle

    dependencies {
        implementation 'com.github.rishabh9:riko:1.0.0-SNAPSHOT'
    }

in case of `riko-vanilla`

    dependencies {
        implementation 'com.github.rishabh9:riko:1.0.0-SNAPSHOT'
    }

#### Progaurd

Riko is built using [Retrofit](http://square.github.io/retrofit/), 
and Retrofit internally uses [OkHttp](https://github.com/square/okhttp) 
and [Okio](https://github.com/square/okio).
Please refer their respective documentation for Progaurd configurations:

- [Retrofit](https://github.com/square/retrofit#proguard)
- [OkHttp](https://github.com/square/okhttp#proguard)
- [Okio](https://github.com/square/okio#proguard)
