#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring

JNICALL
Java_comapisdashboardprojectbadgerbuddy_1187404durationpt1h_google_developers_httpsconsole_badgerbuddy_Start_Page_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
