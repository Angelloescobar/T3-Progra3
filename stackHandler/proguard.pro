-dontshrink
-dontoptimize
-ignorewarnings
-dontwarn **

-keepattributes StackMapTable,StackMap,Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,*Annotation*

-keep public class umg.edu.gt.stackhandler.App {
    public static void main(java.lang.String[]);
}