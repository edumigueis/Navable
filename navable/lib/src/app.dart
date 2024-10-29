class Navable extends StatelessWidget {
  const Navable({
    super.key,
    required this.settingsController,
    required this.profileController,
    required this.mapController,
    required this.pickController,
    required this.signupController,
    required this.signinController,
    required this.isUserSignedIn,
  });

  final SettingsController settingsController;
  final ProfileController profileController;
  final MapViewController mapController;
  final PickAccessibilitiesController pickController;
  final SignupController signupController;
  final SigninController signinController;
  final bool isUserSignedIn;

  @override
  Widget build(BuildContext context) {
    return ListenableBuilder(
      listenable: settingsController,
      builder: (BuildContext context, Widget? child) {
        return MaterialApp(
          restorationScopeId: 'app',
          localizationsDelegates: const [
            AppLocalizations.delegate,
            GlobalMaterialLocalizations.delegate,
            GlobalWidgetsLocalizations.delegate,
            GlobalCupertinoLocalizations.delegate,
          ],
          supportedLocales: const [
            Locale('en', ''), // English, no country code
          ],
          onGenerateTitle: (BuildContext context) =>
              AppLocalizations.of(context)!.appTitle,
          theme: ThemeData(),
          darkTheme: ThemeData.dark(),
          themeMode: settingsController.themeMode,
          onGenerateRoute: (RouteSettings routeSettings) {
            return MaterialPageRoute<void>(
              settings: routeSettings,
              builder: (BuildContext context) {
                // Use isUserSignedIn to determine the initial route
                if (routeSettings.name == '/') {
                  return isUserSignedIn ? 
                    MapView(controller: mapController) : 
                    const Landing();
                }
                switch (routeSettings.name) {
                  case SettingsView.routeName:
                    return SettingsView(controller: settingsController);
                  case ProfileView.routeName:
                    return ProfileView(controller: profileController);
                  case SignUpView.routeName:
                    return SignUpView(controller: signupController);
                  case SignInView.routeName:
                    return SignInView(controller: signinController);
                  case PickAccessibilitiesView.routeName:
                    return PickAccessibilitiesView(controller: pickController);
                  case ReviewView.routeName:
                    return ReviewView(controller: profileController);
                  default:
                    return const Landing();
                }
              },
            );
          },
        );
      },
    );
  }
}
