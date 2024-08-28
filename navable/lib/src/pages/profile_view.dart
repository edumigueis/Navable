import 'package:flutter/material.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';

class ProfileView extends StatelessWidget {
  const ProfileView({super.key, required this.controller});

  static const routeName = '/profile';

  final ProfileController controller;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(),
      body: ListView(
        children: [
          Card(
            elevation: 4,
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8),
            ),
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Row(
                children: [
                  CircleAvatar(
                    radius: 30,
                    backgroundImage: AssetImage('assets/profile_photo.png'), // Replace with your photo asset
                  ),
                  const SizedBox(width: 16),
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'John Doe', // Replace with dynamic name if needed
                          style: Theme.of(context).textTheme.headlineMedium,
                        ),
                        const SizedBox(height: 8),
                        LinearProgressIndicator(
                          value: 0.7, // Static progress, replace with dynamic value if needed
                          backgroundColor: Colors.grey[300],
                          color: Colors.blue,
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(width: 16),
                  IconButton(
                    icon: const Icon(Icons.settings),
                    onPressed: () {
                      Navigator.pushNamed(context, "/settings");
                    },
                  ),
                ],
              ),
            ),
          ),
          const SizedBox(height: 16),
          ExpandableSection(
            title: 'Accessibility',
            child: Column( children: [
              /*Section(
                title: 'Category 1',
                children: List.generate(5, (index) => _buildSelectableButton(context, 'Option ${index + 1}', index)),
              ),
              Section(
                title: 'Category 2',
                children: List.generate(5, (index) => _buildSelectableButton(context, 'Option ${index + 6}', index)),
              ),*/
            ],)
          ),
          ExpandableSection(
            title: 'Badges',
            child: Column()
          ),
        ],
      ),
    );
  }

  Widget _buildSelectableButton(BuildContext context, String text, int index) {
    return StatefulBuilder(
      builder: (context, setState) {
        bool _isSelected = false;

        return GestureDetector(
          onTap: () {
            setState(() {
              _isSelected = !_isSelected;
            });
          },
          child: Container(
            padding: const EdgeInsets.symmetric(
              vertical: 10,
              horizontal: 16,
            ),
            decoration: BoxDecoration(
              color: _isSelected ? Colors.blue : Colors.grey[300],
              borderRadius: BorderRadius.circular(8),
            ),
            child: Text(
              text,
              style: TextStyle(
                color: _isSelected ? Colors.white : Colors.black,
              ),
            ),
          ),
        );
      },
    );
  }
}

class ExpandableSection extends StatefulWidget {
  final String title;
  final Widget child;

  const ExpandableSection({
    super.key,
    required this.title,
    required this.child,
  });

  @override
  _ExpandableSectionState createState() => _ExpandableSectionState();
}

class _ExpandableSectionState extends State<ExpandableSection> {
  bool _isExpanded = false;

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        ListTile(
          title: Text(widget.title),
          trailing: IconButton(
            icon: Icon(
              _isExpanded ? Icons.expand_less : Icons.expand_more,
            ),
            onPressed: () {
              setState(() {
                _isExpanded = !_isExpanded;
              });
            },
          ),
        ),
        if (_isExpanded)
          widget.child
      ],
    );
  }
}
