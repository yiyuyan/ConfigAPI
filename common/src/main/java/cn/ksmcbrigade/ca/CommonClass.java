package cn.ksmcbrigade.ca;

import cn.ksmcbrigade.ca.platform.Services;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class CommonClass {

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {

        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.
        if (Services.PLATFORM.isModLoaded("ca")) {
            Constants.LOG.info("Hello to ca");
        }

        //test
        /*for (int i = 0; i < 10; i++) {
            ConfigBuilder builder = new ConfigBuilder(RandomStringUtils.randomNumeric(4)+".json",true);
            builder.data.put("AConfig","I won't appear in the config screen.But,know i am appeared.");
            builder.data.put("b",666);
            builder.data.put("a",1999.9f);
            builder.data.put("rand",RandomStringUtils.random(10));
            try {
                builder.build();
            } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/
    }
}
