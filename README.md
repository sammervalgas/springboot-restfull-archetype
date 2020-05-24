# springboot-restfull-archetype
Maven archetype to build new projects with Springboot Rest Api following best practices.

## Get Started

Implements your own Restfull Api With SpringBoot easily.

```bash
git clone https://github.com/sammervalgas/springboot-restfull-archetype.git && cd -
mvn clean install -DskipTests

cd TO_YOUR_DIRECTORY/

mvn archetype:generate \
-DgroupId=YOUR_PACKAGE \
-DartifactId=YOUR_PROJECT_NAME \
-Dversion=YOUR_VERSION \ 
-DarchetypeCatalog=local \
-DinteractiveMode=false \
-DarchetypeGroupId=br.com.svalgas \
-DarchetypeArtifactId=springboot-restfull-archetype
    
```

> Note: For more information read [springboot-restfull project](https://github.com/sammervalgas/springboot-restfull).

## Author

* Sammer Valgas
