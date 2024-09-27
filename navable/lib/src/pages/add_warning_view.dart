import 'package:flutter/material.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/pages/models/warning.dart';
import 'package:navable/src/util/styles.dart';

class AddWarningView extends StatelessWidget {
  const AddWarningView({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 400,
      padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 16),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Text(
            "O que você quer reportar?",
            style: Theme.of(context).textTheme.smalltitle,
          ),
          const SizedBox(height: 16), // Add spacing
          Expanded(
            // Wrap GridView in Expanded to give it constrained space
            child: GridView.count(
              crossAxisCount: 3,
              children: [
                Warning("a", "b", const LatLng(2.1, 2.3)),
                Warning("a", "b", const LatLng(2.1, 2.3)),
                Warning("a", "b", const LatLng(2.1, 2.3)),
              ].map((el) {
                return GestureDetector(
                  onTap: () {
                    // Example data to return
                    String selectedData = "Some Filter Data";
                    Navigator.pop(context, selectedData);
                  },
                  child: Column(
                    children: [
                      CircleAvatar(
                        radius: 30,
                        backgroundImage:
                            AssetImage('assets/images/flutter_logo.png'),
                      ),
                      Text(el.title, style: Theme.of(context).textTheme.minititle,),
                    ],
                  ),
                );
              }).toList(),
            ),
          ),
        ],
      ),
    );
  }
}
