plugins {
    id("java")
}

group = "ua.mani123"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/net.dv8tion/JDA
    implementation("net.dv8tion:JDA:5.0.0-beta.6")

    // https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-sync
    implementation("org.mongodb:mongodb-driver-sync:4.9.0")

    //// https://mvnrepository.com/artifact/org.mongodb/mongodb-driver-reactivestreams
    //implementation("org.mongodb:mongodb-driver-reactivestreams:4.9.0")

    // https://mvnrepository.com/artifact/ch.qos.logback/logback-classic
    implementation("ch.qos.logback:logback-classic:1.4.6")

    // https://mvnrepository.com/artifact/org.projectlombok/lombok
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = Charsets.UTF_8.name()
    options.isIncremental = true
    options.release.set(17)
}