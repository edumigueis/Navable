import 'package:flutter/material.dart';
import 'controllers/settings_controller.dart';

class PickAccessibilitiesView extends StatelessWidget {
  const PickAccessibilitiesView({super.key, required this.controller});

  static const routeName = '/pickacc';

  final SettingsController controller;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
      ),
      extendBodyBehindAppBar: true,
      extendBody: true,
      backgroundColor: Colors.white,
      body: Center(
          child: Column(children: [
        Expanded(child: ListView()),
        Padding(
          padding: const EdgeInsets.fromLTRB(15.0, 20.0, 15.0, 20.0),
          child: SizedBox(
            width: double.infinity,
            height: 50,
            child: TextButton(
                onPressed: () {
                  Navigator.pushNamed(context, "/home");
                },
                child: const Text('LOGIN',
                    style: TextStyle(
                        fontSize: 15,
                        fontFamily: 'Ubuntu',
                        fontWeight: FontWeight.bold,
                        color: Color.fromRGBO(240, 240, 240, 1)))),
          ),
        ),
      ])),
    );
  }
}
