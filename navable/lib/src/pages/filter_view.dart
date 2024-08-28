import 'package:flutter/material.dart';

class FilterView extends StatelessWidget {
  const FilterView({
    super.key
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 200,
      margin: EdgeInsets.symmetric(
        horizontal: MediaQuery.of(context).size.width * 0.1,
      ),
      padding: EdgeInsets.all(16.0),
      child: Column(
        children: [
          ElevatedButton(
            onPressed: () {
              // Example data to return
              String selectedData = "Some Filter Data";
              Navigator.pop(context, selectedData); // Pass data back
            },
            child: const Text('Apply Filter'),
          ),
        ],
      ),
    );
  }
}