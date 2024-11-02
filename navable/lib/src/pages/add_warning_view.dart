import 'package:flutter/material.dart';
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
          const SizedBox(height: 20), // Add spacing
          Expanded(
            child: GridView.count(
              crossAxisCount: 3,
              mainAxisSpacing: 10,
              crossAxisSpacing: 10,
              children: warnings.map((el) {
                return GestureDetector(
                  onTap: () {
                    Navigator.pop(context, el);
                  },
                  child: Column(
                    mainAxisSize: MainAxisSize.min,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      const CircleAvatar(
                        radius: 30,
                        backgroundImage: AssetImage('assets/images/flutter_logo.png'),
                      ),
                      const SizedBox(height: 4),
                      Text(
                        el.nome.length > 20 ? '${el.nome.substring(0, 20)}...' : el.nome,
                        style: Theme.of(context).textTheme.highlight,
                        textAlign: TextAlign.center,
                      ),
                    ],
                  ),
                );
              }).toList(),
            )
          ),
        ],
      ),
    );
  }
}
