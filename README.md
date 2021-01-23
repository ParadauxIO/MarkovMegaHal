# MarkovMegaHal

# MarkovMegaHal
[![Nexus](https://img.shields.io/nexus/r/io.paradaux.http/HttpApi?color=66b3b3&label=api-version&nexusVersion=3&server=https%3A%2F%2Frepo.paradaux.io)](https://repo.paradaux.io/#browse/browse:maven-releases)
[![Discord](https://img.shields.io/discord/583254829279739905?label=Support%20Discord%21)](https://paradaux.io/discord)
[![Jenkins](https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fci.paradaux.io%2Fjob%2FHttpApi%2F&label=jenkins%20build)](https://ci.paradaux.io/job/HttpApi/)

## What is MegaHal

> MegaHAL is a computer conversation simulator, or "chatterbot", created by Jason Hutchens.

[Wikipedia: MegaHal](https://en.wikipedia.org/wiki/MegaHAL)

MarkovMegaHal is a Java fork of the JMegaHal project to bring it in line with Java 1.8 and to provide access for users to use conventional dependency management tools (i.e. Maven) in order to implement MegaHal into their own projects.
[Original JMegaHal project](http://www.jibble.org/jmegahal/)

## Maven

MarkovMegaHal isn't currently available in central, however you can find it in my repository.
```xml
<repositories>
    <repository>
        <id>paradaux</id>
        <url>https://repo.paradaux.io/repository/maven-releases/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>io.paradaux.ai</groupId>
        <artifactId>MarkovMegaHal</artifactId>
        <version>%VERSION% REPLACE THIS WITH THE NUMBER IN THE FIRST BADGE</version>
    </dependency>
</dependencies>
```

# License
This software is dual-licensed, allowing you to choose between the GNU
General Public License (GPL), and the www.jibble.org Commercial License.
Since the GPL may be too restrictive for use in a proprietary application,
a commercial license is also provided. Full license information can be found at http://www.jibble.org/licenses/
