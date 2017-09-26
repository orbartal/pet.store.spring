# pet.store.spring
A demo micro service  spring boot back end of the swagger pet store example


I will use the clear architecture principle:
Isolate the project internal business logic from the frameworks you use.
And each component (db, ui, utils...) should only depend on the project internals,
not any other component, so the component will be Independent, and can be easily tested and replaced:
https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html 

The implementation of the clear architecture will be accomplished by separating the project components into different maven models. 
Each of its own independent maven build, dependencies and test cases. 
I will also create a maven project that will contain the main and the common configuration and connect all the other components to create one project that can be built to run and test at the touch of a button.
