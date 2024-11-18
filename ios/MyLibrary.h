
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNMyLibrarySpec.h"

@interface MyLibrary : NSObject <NativeMyLibrarySpec>
#else
#import <React/RCTBridgeModule.h>

@interface MyLibrary : NSObject <RCTBridgeModule>
#endif

@end
