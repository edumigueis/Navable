import 'package:flutter/material.dart';

class Landing extends StatefulWidget{
  const Landing({required Key key}) : super(key: key);

  @override
  LandingState createState() => LandingState();
}
class LandingState extends State<Landing>{

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            Row(
              children: <Widget>[
                Expanded(
                  flex: 10, // 20%
                  child: Image.asset(
                    'assets/images/main.jpg',
                    fit: BoxFit.cover,
                    height: MediaQuery.of(context).size.height * 0.56,
                  ),
                ),
              ],
            ),
            const Padding(
              padding: EdgeInsets.fromLTRB(8.0, 30.0, 8.0, 10.0),
              child: Text(
                "Welcome to NavAble!",
                style: TextStyle(
                    fontSize: 25.0,
                    fontFamily: 'Ubuntu',
                    fontWeight: FontWeight.w600),
              ),
            ),
            const Padding(
              padding: EdgeInsets.fromLTRB(8.0, 20.0, 8.0, 20.0),
              child: Text(
                "The biggest and most inclusive clothing e-commerce. Enjoy the amazing propped app, with everything you need.",
                style: TextStyle(
                    fontSize: 18.0,
                    fontFamily: 'Ubuntu',
                    fontWeight: FontWeight.w500,
                    color: Color.fromRGBO(40, 40, 40, 1)),
                textAlign: TextAlign.center,
              ),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Padding(
                    padding: const EdgeInsets.fromLTRB(8.0, 20.0, 8.0, 10.0),
                    child: SizedBox(
                      width: 150,
                      height: 50,
                      child: TextButton(
                          onPressed: () {

                          },
                          child: const Text('LOGIN',
                              style: TextStyle(
                                  fontSize: 17,
                                  fontFamily: 'Ubuntu',
                                  fontWeight: FontWeight.bold,
                                  color: Color.fromRGBO(0, 0, 0, 10)))),
                    )),
                Padding(
                    padding: const EdgeInsets.fromLTRB(8.0, 20.0, 8.0, 10.0),
                    child: SizedBox(
                      width: 150,
                      height: 50,
                      child: TextButton(
                          onPressed: () {

                          },
                          child: const Text('REGISTER',
                              style: TextStyle(
                                  fontSize: 17,
                                  fontFamily: 'Ubuntu',
                                  fontWeight: FontWeight.bold,
                                  color: Color.fromRGBO(240, 240, 240, 1)))),
                    ))
              ],
            ),
          ],
        ),
      ),
    );
  }
}