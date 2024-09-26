import 'package:flutter/material.dart';
import 'package:navable/src/util/styles.dart';

import '../components/basics/navable_button.dart';

class Landing extends StatefulWidget {
  const Landing({super.key});

  @override
  LandingState createState() => LandingState();
}

class LandingState extends State<Landing> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF3FAE1),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            Expanded(
                flex: 60,
                child: Container(
                  color: const Color(0xFF5E2BFF),
                )),
            Padding(
                padding: EdgeInsets.only(top: 20, bottom: 10),
                child: Text("Bem-vindo à Navable!",
                    style: Theme.of(context).textTheme.subtitle)),
            Padding(
              padding: EdgeInsets.symmetric(horizontal: 40),
              child: Text(
                "O seu mapa colaborativo de acessibilidade. Vá onde quiser quando quiser.",
                style: Theme.of(context).textTheme.body,
                textAlign: TextAlign.center,
              ),
            ),
            const SizedBox(height: 20),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 20, vertical: 20),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  NavableButton(
                    "CRIAR CONTA",
                    onPressed: () {
                      Navigator.pushNamed(context, "/signup");
                    },
                  ),
                  const SizedBox(height: 8),
                  NavableButton(
                    "ENTRAR",
                    background: Colors.transparent,
                    onPressed: () {
                      Navigator.pushNamed(context, "/signin");
                    },
                  )
                ],
              ),
            )
          ],
        ),
      ),
    );
  }
}
