### Android Application for Tecnoesis 2020



**Required libraries :** 

0: **Dagger Android** : Dependency Injection Framework

1: Butterknife

2: Android Architecture Components -- ViewModel and LiveData

3: Timber : for logging

4: Retrofit and GSON : for Api Requests

5: Material Design Components MDC


## Naming Patterns

**layouts**
`type_name`




|||
|--|--|
|MainActivity|activity_main|
|HomeFragment|fragment_home|
|for listLayouts in recyclers etc|item_user|

**id naming**

    type_context_viewType_purpose

|desc|id|
|-|-|
|TextView in MainActivty|act_main_tv_headline|
|Button in MainActivty|act_main_btn_submit|
|TextView in HomeFragment|frag_home_tv_headline|




## Project Structure

    |-- tenx
        |-- base
        |   |-- BaseApplication.java
        |   |-- BaseViewModel.java
        |   |-- ViewModelFactory.java
        |-- config
        |   |-- Config.java
        |-- data
        |   |-- AppDataManager.java
        |   |-- AppDataManagerHelper.java
        |   |-- models
        |   |   |-- EventResponse.java
        |   |-- rest
        |       |-- events
        |           |-- AppEventHelper.java
        |           |-- EventsApiService.java
        |-- di
        |   |-- components
        |   |   |-- AppComponent.java
        |   |-- modules
        |   |   |-- app
        |   |   |   |-- ActivityBuilderModule.java
        |   |   |   |-- AppModule.java
        |   |   |   |-- AppRetrofitModule.java
        |   |   |-- home
        |   |   |   |-- HomeFragmentModule.java
        |   |   |-- main
        |   |       |-- FragmentBuilderModule.java
        |   |       |-- MainActModule.java
        |   |-- scopes
        |       |-- ActivityContext.java
        |       |-- ApplicationContext.java
        |       |-- FragmentScope.java
        |       |-- PerActivity.java
        |-- logging
        |   |-- ReleaseTree.java
        |-- ui
        |   |-- main
        |   |   |-- MainActivity.java
        |   |   |-- MainViewModel.java
        |   |   |-- MainViewModelHelper.java
        |   |   |-- about
        |   |   |   |-- AboutFragment.java
        |   |   |   |-- AboutViewModel.java
        |   |   |-- events
        |   |   |   |-- EventsFragment.java
        |   |   |   |-- EventsViewModel.java
        |   |   |-- home
        |   |   |   |-- HomeFragment.java
        |   |   |   |-- HomeViewModel.java
        |   |   |-- schedule
        |   |       |-- ScheduleFragment.java
        |   |       |-- ScheduleViewModel.java
        |   |-- splash
        |       |-- SplashActivity.java
        |-- utils

| directory/files |purpose  |
|--|--|
|  `data`| Contains Data Services , REST calls , Firebase, Shared Prefs etc  |
|`AppDataManager`|Encapsulates all data sources into a single entity by combining the interfaces and implementing them|
|`base`|Contains Base classes|
|`di`|Contains di modules and components|
|`di->modules`|has modules for fragments and activities. They provide dependencies|
|`di->components`|has components for dagger|
|`logging`|This is not setup yet. It should upload errors to server|
|`ui`|contains the Activities and Fragments . The hierarchy is followed as Application ==> Activities ==> Fragments. An Application is a single entity of the application. But it can have multiple Activities. Each Activity also can have multiple fragments.|
|`ViewModels`|Each Activity and Fragment has its own view model. View model connects data to view. Remember the FragmentViewModel will live as long as the Fragment but its containing Activity will persist. So you have to decide wisely which Viewmodel to use and when. Also note that it is possible for an Application to only use its `own` viewmodel (not implemented here) and not others. An Activity can access its `own` view model and `appViewModel`. A Fragment View Model can access all three view models. This is because of the heirarchy stated above |
|`config`|contains configuration keys|


