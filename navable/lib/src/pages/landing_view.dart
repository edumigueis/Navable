import 'package:flutter/material.dart';

import '../components/navable_button.dart';

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
                flex: 60, // 20%
                child: Container(
                  color: const Color(0xFF5E2BFF),
                )),
            const Padding(
              padding: EdgeInsets.only(top: 20, bottom: 10),
              child: Text(
                "Welcome to navAble!",
                style: TextStyle(
                    fontSize: 22.0,
                    fontFamily: 'Ubuntu',
                    fontWeight: FontWeight.w600),
              ),
            ),
            const Padding(
              padding: EdgeInsets.symmetric(horizontal: 40),
              child: Text(
                "Lorem Ipsum Dolor kaakuaua aa yaa aja ha aaiayavqyqy7aa aa hayuaysvbsa abava aayava.",
                style: TextStyle(
                    fontSize: 16.0,
                    fontFamily: 'Ubuntu',
                    fontWeight: FontWeight.w400,
                    color: Color.fromRGBO(40, 40, 40, 1)),
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
                    "REGISTER",
                    onPressed: () {
                      Navigator.pushNamed(context, "/signup");
                    },
                  ),
                  const SizedBox(height: 8),
                  NavableButton(
                    "SIGN IN",
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
