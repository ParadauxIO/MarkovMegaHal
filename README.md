# MarkovMegaHal

[![Nexus](https://img.shields.io/nexus/r/io.paradaux.ai/MarkovMegaHal?color=66b3b3&label=version&nexusVersion=3&server=https%3A%2F%2Frepo.paradaux.io)](https://repo.paradaux.io/#browse/browse:maven-releases)
[![Discord](https://img.shields.io/discord/583254829279739905?label=Support%20Discord%21)](https://paradaux.io/discord)
[![Jenkins](https://img.shields.io/jenkins/build?jobUrl=https%3A%2F%2Fci.paradaux.io%2Fjob%2FMarkovMegaHal%2F)](https://ci.paradaux.io/job/MarkovMegaHal/)

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
        <url>https://repo.paradaux.io/releases</url>
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
## Usage

MarkovMegaHal is very simple to use, the first step is to create an instance of the chatterbot like so:
```java
MarkovMegaHal megaHal = new MarkovMegaHal();
```

At which point you now have a chatterbot with no "brain" i.e it has no vocabulary, or anything to base its knowledge off of. In order to teach it we have to feed it sentences, like so:
```java
megaHal.add("Hello, My name is Chatterbot!");
```

You need to feed it a significant amount of sentences (~ 500+ ) for it to begin mixing and matching the words you feed it to generate unique material. Until then, it will simply feed back one of the sentences you used to train it. 

There are two methods to generate sentences, one with no input material, and another where you give it a word to "target" in which it will attempt to generate a sentence containing the specified word.

The following will generate a sentence.
```java
String sentence = megaHall.getSentence();
```

And the following is how you supply a target word:
```java
String sentence = megaHall.getSentence("Hello");
```

You can also add material via files, provided a String uri like so:

```java
megaHal.addDocument("/home/paradaux/my-textfile.txt");
```

# License
This software is dual-licensed, allowing you to choose between the GNU
General Public License (GPL), and the www.jibble.org Commercial License.
Since the GPL may be too restrictive for use in a proprietary application,
a commercial license is also provided. Full license information can be found at http://www.jibble.org/licenses/
