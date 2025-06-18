import 'package:flutter/material.dart';
import 'package:navable/src/components/basics/navable_button.dart';
import 'package:navable/src/util/styles.dart';

import '../components/accessibility_checks.dart';
import '../components/basics/expandable_section.dart';
import 'models/acc_category.dart';

class FilterView extends StatefulWidget {
  final Function(String) onApplyFilters; // Callback when applying filters

  const FilterView({super.key, required this.onApplyFilters});

  @override
  _FilterViewState createState() => _FilterViewState();
}

class _FilterViewState extends State<FilterView> {
  double _rating = 2; // State for rating filter
  List<AccessibilityCategory> _selectedAccessibilityCategories = [];
  List<AccessibilityCategory> _selectedEstablishmentTypes = [];

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 400,
      child: ListView(
        padding: const EdgeInsets.symmetric(horizontal: 15, vertical: 20),
        children: [
          Text(
            'Filtrar',
            style: Theme.of(context).textTheme.subtitle,
          ),
          SizedBox(height: 10),
          Card(
            child: Padding(
              padding: const EdgeInsets.all(10),
              child: Column(
                children: [
                  Text("Avaliação"),
                  Slider(
                    value: _rating,
                    min: 1,
                    max: 5,
                    divisions: 4,
                    label: _rating.toString(),
                    onChanged: (double value) {
                      setState(() {
                        _rating = value;
                      });
                    },
                  ),
                ],
              ),
            ),
          ),
          Card(
            child: Padding(
              padding: const EdgeInsets.all(10),
              child: ExpandableSection(
                title: 'Tipos de estabeleciemento',
                child: Center(child: AccessibilityChecks(
                  title: "Tipos",
                  buttons: [
                    AccessibilityCategory(1, "a", "b"),
                    AccessibilityCategory(1, "b", "b"),
                    AccessibilityCategory(1, "c", "a")
                  ],
                  onSelectionChanged: (selectedCategories) {
                    setState(() {
                      _selectedEstablishmentTypes = selectedCategories;
                    });
                  },
                )),
              ),
            ),
          ),
          SizedBox(height: 30),
          NavableButton(
            "APLICAR",
            onPressed: () {
              String selectedData = // Prepare your filter criteria based on state variables
                  'Rating: $_rating, Accessibility: ${_selectedAccessibilityCategories.join(", ")}, Establishments: ${_selectedEstablishmentTypes.join(", ")}';
              widget.onApplyFilters(selectedData);
              Navigator.pop(context);
            },
          ),
        ],
      ),
    );
  }
}