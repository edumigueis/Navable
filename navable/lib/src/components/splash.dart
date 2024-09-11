import 'package:flutter/material.dart';

import '../util/styles.dart';

class SplashScreen extends StatelessWidget {
  const SplashScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      backgroundColor: NavableColors.blueAccent,
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            FlutterLogo(),
            SizedBox(height: 20),
            CircularProgressIndicator(
              color: NavableColors.white,
            )
          ],
        ),
      ),
    );
  }
}
