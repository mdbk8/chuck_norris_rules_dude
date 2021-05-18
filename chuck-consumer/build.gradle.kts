import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"

    id("info.solidsoft.pitest") version "1.6.0"
}

group = "com.dybek.chuckconsumer"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(15))
    }
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.kafka:spring-kafka")
    implementation(
        group = "org.springframework.cloud",
        name = "spring-cloud-starter-openfeign",
        version = "3.0.2"
    )

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.mockito.kotlin:mockito-kotlin:3.1.0")
    testImplementation("com.tngtech.archunit:archunit:0.18.0")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.awaitility:awaitility:4.0.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    testImplementation(
        group = "com.github.tomakehurst",
        name = "wiremock",
        version = "2.27.2"
    )

    testImplementation(
        group = "org.springframework.cloud",
        name = "spring-cloud-starter-contract-stub-runner",
        version = "3.0.2"
    )

    testImplementation(
        group = "com.marcinziolo",
        name = "kotlin-wiremock",
        version = "1.0.0"
    )
}

sourceSets {
    test {
        java {
            srcDirs(
                "src/test/common",
                "src/test/unit",
                "src/test/integration",
                "src/test/functional"
            )
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "15"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

pitest {
    //adds dependency to org.pitest:pitest-junit5-plugin and sets "testPlugin" to "junit5"
    junit5PluginVersion.set("0.14")
    timestampedReports.set(false)

    targetClasses.set(listOf(
        "com.dybek.chuckconsumer.*"
    ))
    targetTests.set(listOf(
        "com.dybek.chuckconsumer.*"
    ))

    avoidCallsTo.set(listOf(
        "kotlin.jvm.internal"
    ))
}