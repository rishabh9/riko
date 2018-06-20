## Riko

Riko is a third-party Java library for accessing Upstox API.

### Including Riko in your project

#### Maven

    <dependency>
        <groupId>com.github.rishabh9</groupId>
        <artifactId>riko</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

#### Gradle

    dependencies {
        implementation 'com.github.rishabh9:riko:1.0-SNAPSHOT'
    }


#### Riko internally depends on

- Retrofit2
- OkHttp3
- Google Gson
- Google Guava
- Log4J 2
- Junit 5

### The NumberString

The Upstox API that fetches the current positions for the day, returns certain numeric data as empty String if no value
exists for the same. In a dynamic typed language this may work, but in a strongly typed language like Java, this causes 
a lot of pain. To support such data, we have the NumberString class and the support (de)serializers.

### Progaurd

Riko is built using [Retrofit](http://square.github.io/retrofit/), and Retrofit internally uses [OkHttp](https://github.com/square/okhttp) 
and [Okio](https://github.com/square/okio).
Please refer their respective documentation for Progaurd configurations:

- [Retrofit](https://github.com/square/retrofit#proguard)
- [OkHttp](https://github.com/square/okhttp#proguard)
- [Okio](https://github.com/square/okio#proguard)
