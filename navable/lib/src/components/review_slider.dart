import 'package:flutter/material.dart';

class ReviewSlider extends StatefulWidget {
  final double initialValue;
  final ValueChanged<double> onChanged;

  const ReviewSlider({
    Key? key,
    required this.initialValue,
    required this.onChanged,
  }) : super(key: key);

  @override
  ReviewSliderState createState() => ReviewSliderState();
}

class ReviewSliderState extends State<ReviewSlider> {
  late double sliderValue;

  @override
  void initState() {
    super.initState();
    sliderValue = widget.initialValue;
  }

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        getIconForValue(sliderValue),
        const SizedBox(width: 10),
        Expanded(
          child: Slider(
            value: sliderValue,
            min: 0,
            max: 2,
            divisions: 2,
            onChanged: (newValue) {
              setState(() {
                sliderValue = newValue;
              });
              widget.onChanged(newValue);
            },
            activeColor: getColorForValue(sliderValue),
          ),
        ),
      ],
    );
  }

  // Function to get the color based on the slider value
  Color getColorForValue(double value) {
    if (value == 0) {
      return Colors.red;
    } else if (value == 1) {
      return Colors.yellow;
    } else {
      return Colors.green;
    }
  }

  // Function to get the icon based on the slider value
  Icon getIconForValue(double value) {
    if (value == 0) {
      return Icon(Icons.close, color: Colors.red);
    } else if (value == 1) {
      return Icon(Icons.thumb_up, color: Colors.yellow);
    } else {
      return Icon(Icons.favorite, color: Colors.green);
    }
  }
}
