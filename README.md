# CIP4 JDF Java Library
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.cip4.lib.jdf/JDFLibJ/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.cip4.lib.jdf/JDFLibJ) [![License (CIP4 Software License)](https://img.shields.io/badge/license-CIP4%20Software%20License-blue)](https://github.com/cip4/xJdfLib/blob/master/LICENSE.md) ![Snapshot](https://github.com/cip4/JDFLibJ/workflows/Snapshot/badge.svg)

CIP4 JDF Library


### Download

Gradle:
```gradle
dependencies {
  implementation 'org.cip4.lib.jdf:JDFLibJ:2.1.7.3'
}
```

Maven:
```xml
<dependency>
  <groupId>org.cip4.lib.jdf</groupId>
  <artifactId>JDFLibJ</artifactId>
  <version>2.1.7.3</version>
</dependency>
```

[JDFLibJ jar downloads](https://maven-badges.herokuapp.com/maven-central/org.cip4.lib.jdf/JDFLibJ) are available from Maven Central.


## Issue Tracking
Don't write issues, provide Pull-Requests!

## Development Notes
### Release a new Version
Creation and publishing of a new version to GitHub Release and to the Central Maven Repository. 

**NOTE:** The publication to the Central Maven Repository may take up to two hours.

```bash
$ git tag -a [VERSION] -m "[TITLE]"
$ git push origin [VERSION]
```

In case a build has been failed, a tag can be deleted using the following command:
```bash
$ git tag -d [VERSION]
$ git push origin :refs/tags/[VERSION]
```
