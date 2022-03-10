package demo;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.fixes.SuggestedFix;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.google.errorprone.matchers.Matchers;
import com.sun.source.tree.AnnotationTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.ImportTree;

import java.util.List;

@AutoService(BugChecker.class)
@BugPattern(
        name = "LombokDataAndBuilder",
        linkType = BugPattern.LinkType.CUSTOM,
        link = "https://projectlombok.org/features/Value",
        tags = BugPattern.StandardTags.FRAGILE_CODE,
        summary = "@lombok.Builder and @lombok.Data rarely need to be attached to a class at once.",
        explanation =
                "@lombok.Builder may be useless for fully mutable @lombok.Data class. "
                        + "Consider to make the class immutable, or omit @lombok.Builder for fully mutable @lombok.Data class.",
        severity = BugPattern.SeverityLevel.WARNING)
@SuppressWarnings("serial")
public class LombokDataAndBuilder extends BugChecker implements BugChecker.ImportTreeMatcher {
    private static final Matcher<AnnotationTree> IS_DATA = Matchers.isType("lombok.Data");
    private static final Matcher<AnnotationTree> IS_BUILDER = Matchers.isType("lombok.Builder");
//    @Override
//    public Description matchClass(ClassTree tree, VisitorState state) {
////        System.out.println(tree);
////        return Description.builder(null,"name",null,null,"hello").build();
//        return Description.builder(tree,"name","link", BugPattern.SeverityLevel.ERROR,"hello").build();
//    }

    @Override
    public Description matchImport(ImportTree tree, VisitorState state) {
        System.out.println(tree.getQualifiedIdentifier());
        return Description.NO_MATCH;
//        return Description.builder(tree,"name","link", BugPattern.SeverityLevel.ERROR,"hello").build();
    }

//    @Override
//    public Description matchClass(ClassTree tree, VisitorState state) {
//        return null;
//    }
}
//@RunWith(JUnit4.class)
//public class LombokDataAndBuilderTest {
//    private final CompilationTestHelper compilationTestHelper =
//            CompilationTestHelper.newInstance(LombokDataAndBuilder.class, getClass());
//    @Test
//    public void test() {
//        compilationTestHelper
//                .addSourceLines(
//                        "Test.java",
//                        "// BUG: Diagnostic contains: LombokDataAndBuilder",
//                        "@lombok.Data @lombok.Builder class Test {}")
//                .doTest();
//    }
//}