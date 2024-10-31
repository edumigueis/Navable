import 'package:flutter/material.dart';
import 'package:latlong2/latlong.dart';
import 'package:navable/src/pages/models/warning.dart';
import 'package:navable/src/util/styles.dart';

class AddWarningView extends StatelessWidget {
  const AddWarningView({super.key, required this.warnings});
  final List<WarningType> warnings;

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
            child: GridView.count(
              crossAxisCount: 3,
              children: warnings.map((el) {
                return GestureDetector(
                  onTap: () {
                    Navigator.pop(context, el);
                  },
                  child: Column(
                    children: [
                      CircleAvatar(
                        radius: 30,
                        backgroundImage:
                            AssetImage('assets/images/flutter_logo.png'),
                      ),
                      Text(el.nome, style: Theme.of(context).textTheme.minititle,),
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
