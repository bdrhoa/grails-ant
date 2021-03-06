class GrailsAntGrailsPlugin {
    // the plugin version
    def version = "0.1.3"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.2.3 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
		"grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def author = "Brad Rhoads"
    def authorEmail = "bdrhoa@gmail.com"
    def title = "Grails Ant Plugin"
    def description = '''
Provides ant to Grails applications
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/grails-ant"
    
    def observe = [ 'controllers' ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
    }
        
    def addAnt(clz,log) {
                
        def antBuilder = new AntBuilder()
        clz.metaClass.getAnt = { antBuilder }
            
    }
        
    def doWithDynamicMethods = { ctx ->
        
        application.allClasses.each { clz ->
            addAnt(clz,log)
        }
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
                
        if (application.isControllerClass(event.source)) {
            def controllerClass = application.getControllerClass(event.source?.name)

            // If no GrailsClass can be found, i.e. 'controllerClass'
            // is null, then this is a new controller.
            if (controllerClass == null) {
                controllerClass = application.addArtefact(ControllerArtefactHandler.TYPE, event.source)
            }

            log.info "Add ant to ${controllerClass.shortName}"
            addAnt(controllerClass, log)
        }
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
