package com.dybek.chuckproducer

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.Configuration

private const val BASE_PACKAGE = "com.dybek.chuckproducer"
private const val DOMAIN_PACKAGE = "$BASE_PACKAGE.domain"
private const val INTERFACES_PACKAGE = "$BASE_PACKAGE.interfaces"
private const val INFRASTRUCTURE_PACKAGE = "$BASE_PACKAGE.infrastructure"

class ArchitectureTest {

    private val importer = ClassFileImporter().withImportOption(ImportOption.DoNotIncludeTests())
    private val domainClasses = importer.importPackages(DOMAIN_PACKAGE).`as`("DOMAIN")
    private val allClasses = importer.importPackages(BASE_PACKAGE).`as`("ALL")

    @Test
    fun `domain doesn't depend on infrastructure layer`() =
        noClasses()
            .that().resideInAPackage(DOMAIN_PACKAGE)
            .should()
            .dependOnClassesThat().resideInAPackage("$INFRASTRUCTURE_PACKAGE..")

            .check(domainClasses)

    @Test
    fun `domain doesn't depend on interfaces layer`() =
        noClasses()
            .that().resideInAPackage(DOMAIN_PACKAGE)
            .should()
            .dependOnClassesThat().resideInAPackage("$INTERFACES_PACKAGE..")

            .check(domainClasses)

    @Test
    fun `domain doesn't depend on any concrete external tech`() =
        noClasses()
            .that().resideInAPackage(DOMAIN_PACKAGE)
            .should()
            .dependOnClassesThat().resideOutsideOfPackages(
                "java..",
                "org.slf4j",
                "kotlin..",
                "org.jetbrains.annotations..",
                "$DOMAIN_PACKAGE.."
            )

            .check(domainClasses)

    @Test
    fun `configuration classes ends with "Configuration"`() =
        classes()
            .that().areAnnotatedWith(Configuration::class.java)
            .should().haveSimpleNameEndingWith("Configuration")

            .check(allClasses)
}