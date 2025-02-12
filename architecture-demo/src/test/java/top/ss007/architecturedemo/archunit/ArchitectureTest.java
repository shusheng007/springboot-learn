package top.ss007.architecturedemo.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = {"top.ss007.architecturedemo"}, importOptions = {
        ImportOption.DoNotIncludeTests.class})
public class ArchitectureTest {

    private static final String FACADE_LAYER = "Facade";
    private static final String INFRASTRUCTURE_LAYER = "Infrastructure";
    private static final String APPLICATION_LAYER = "Application";
    private static final String DOMAIN_LAYER = "Domain";

    @ArchTest
    private static final ArchRule LAYERED_ARCHITECTURE =
            layeredArchitecture().consideringOnlyDependenciesInLayers()
                    .layer(FACADE_LAYER).definedBy("..facade..")
                    .layer(INFRASTRUCTURE_LAYER).definedBy("..infrastructure..")
                    .layer(APPLICATION_LAYER).definedBy("..application..")
                    .layer(DOMAIN_LAYER).definedBy("..domain..")

                    .whereLayer(FACADE_LAYER).mayNotBeAccessedByAnyLayer()
                    .whereLayer(INFRASTRUCTURE_LAYER).mayNotBeAccessedByAnyLayer()
                    .whereLayer(APPLICATION_LAYER).mayOnlyBeAccessedByLayers(FACADE_LAYER, INFRASTRUCTURE_LAYER)
                    .whereLayer(DOMAIN_LAYER).mayNotAccessAnyLayer();


    @ArchTest
    public static void archTest(JavaClasses classes) {
        LAYERED_ARCHITECTURE.check(classes);
    }


}
