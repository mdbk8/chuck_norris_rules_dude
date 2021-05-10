package com.dybek.chuckconsumer

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.jupiter.api.Test

private const val BASE_PACKAGE = "com.dybek.chuckconsumer"
private const val DOMAIN_PACKAGE = "$BASE_PACKAGE.domain"
private const val INFRASTRUCTURE_PACKAGE = "$BASE_PACKAGE.infrastructure"
private const val INTERFACES_PACKAGE = "$BASE_PACKAGE.interfaces"

class ArchitectureTest {

    private val importer = ClassFileImporter().withImportOption(ImportOption.DoNotIncludeTests())
    private val domainClasses = importer.importPackages(DOMAIN_PACKAGE).`as`("DOMAIN")

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
                "com.github.javafaker..",
                "$DOMAIN_PACKAGE.."
            )

            .check(domainClasses)
}