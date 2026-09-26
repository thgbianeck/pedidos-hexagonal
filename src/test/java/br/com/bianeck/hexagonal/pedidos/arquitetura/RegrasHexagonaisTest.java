package br.com.bianeck.hexagonal.pedidos.arquitetura;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class RegrasHexagonaisTest {

    private final com.tngtech.archunit.core.domain.JavaClasses classes =
        new ClassFileImporter().importPackages("br.com.bianeck.hexagonal.pedidos");

    @Test
    void dominioNaoDeveDependerDeSpringJpaOuKafka() {
        ArchRule regra = noClasses()
            .that().resideInAPackage("br.com.bianeck.hexagonal.pedidos.domain..")
            .should().dependOnClassesThat()
            .resideInAnyPackage(
                "org.springframework..",
                "jakarta.persistence..",
                "org.apache.kafka..");

        regra.check(classes);
    }

    @Test
    void applicationNaoDeveDependerDeInfraestrutura() {
        ArchRule regra = noClasses()
            .that().resideInAPackage("br.com.bianeck.hexagonal.pedidos.application..")
            .should().dependOnClassesThat()
            .resideInAPackage("br.com.bianeck.hexagonal.pedidos.infraestrutura..");

        regra.check(classes);
    }
}
