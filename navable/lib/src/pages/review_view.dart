import 'package:flutter/material.dart';
import 'package:navable/src/components/basics/navable_button.dart';
import 'package:navable/src/components/basics/navable_text_input.dart';
import 'package:navable/src/components/review_slider.dart';
import 'package:navable/src/pages/controllers/profile_controller.dart';
import 'package:navable/src/pages/controllers/review_controller.dart';
import 'package:navable/src/pages/models/place.dart';
import 'package:navable/src/util/styles.dart';

class ReviewView extends StatefulWidget {
  const ReviewView({super.key, required this.controller});

  static const routeName = '/review';

  final ReviewController controller;

  @override
  ReviewViewState createState() => ReviewViewState();
}

class ReviewViewState extends State<ReviewView> {
  double sliderValue = 1;
  final TextEditingController _reviewController = TextEditingController();
  bool _isSubmitEnabled = false;

  @override
  void initState() {
    super.initState();
    _reviewController.addListener(_updateSubmitButton);
  }

  @override
  void dispose() {
    _reviewController.dispose();
    super.dispose();
  }

  void _updateSubmitButton() {
    setState(() {
      _isSubmitEnabled = _reviewController.text.trim().isNotEmpty;
    });
  }

  @override
  Widget build(BuildContext context) {
    final place = ModalRoute.of(context)!.settings.arguments as Place;

    return Scaffold(
      extendBodyBehindAppBar: true,
      extendBody: true,
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        title: Text('Avaliar ${place.name}'),
        centerTitle: true,
      ),
      body: Stack(
        children: [
          // Background image with gradient overlay
          Container(
            height: 220,
            width: double.infinity,
            decoration: BoxDecoration(
              image: DecorationImage(
                image: NetworkImage(place.image),
                fit: BoxFit.cover,
              ),
            ),
            child: Container(
              decoration: BoxDecoration(
                gradient: LinearGradient(
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                  colors: [
                    Colors.transparent,
                    Colors.black.withOpacity(0.7),
                  ],
                ),
              ),
            ),
          ),

          // Review content
          SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                SizedBox(height: 180),

                // Place info card
                Container(
                  width: double.infinity,
                  decoration: BoxDecoration(
                    color: Theme.of(context).scaffoldBackgroundColor,
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(20),
                      topRight: Radius.circular(20),
                    ),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.black.withOpacity(0.1),
                        blurRadius: 10,
                        offset: Offset(0, -5),
                      ),
                    ],
                  ),
                  padding: const EdgeInsets.all(20),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        place.name,
                        style: Theme.of(context).textTheme.bodyMedium?.copyWith(
                              fontWeight: FontWeight.bold,
                            ),
                      ),
                      SizedBox(height: 4),
                      Row(
                        children: [
                          Icon(Icons.location_on, size: 16, color: Colors.grey),
                          SizedBox(width: 4),
                          Expanded(
                            child: Text(
                              place.address,
                              style: Theme.of(context).textTheme.caption,
                            ),
                          ),
                        ],
                      ),
                      Divider(height: 30),

                      // Rating section
                      Text(
                        'Como você avalia a acessibilidade?',
                        style: Theme.of(context).textTheme.subtitle.copyWith(
                              fontWeight: FontWeight.bold,
                            ),
                      ),
                      SizedBox(height: 20),
                      ReviewSlider(
                        initialValue: sliderValue,
                        onChanged: (newValue) {
                          setState(() {
                            sliderValue = newValue;
                          });
                        },
                      ),
                      SizedBox(height: 10),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          Text('Ruim', style: TextStyle(color: Colors.grey)),
                          Text('Regular', style: TextStyle(color: Colors.grey)),
                          Text('Bom', style: TextStyle(color: Colors.grey)),
                          Text('Excelente',
                              style: TextStyle(color: Colors.grey)),
                        ],
                      ),
                      SizedBox(height: 30),

                      // Review text field
                      Text(
                        'Compartilhe sua experiência',
                        style: Theme.of(context).textTheme.subtitle?.copyWith(
                              fontWeight: FontWeight.bold,
                            ),
                      ),
                      SizedBox(height: 15),
                      NavableTextInput(
                        "Conte-nos como foi sua experiência com acessibilidade em ${place.name}...",
                        controller: _reviewController,
                        maxLines: 6,
                      ),

                      // Error message
                      if (widget.controller.errorMessage != null)
                        Padding(
                          padding: const EdgeInsets.only(top: 16),
                          child: Text(
                            widget.controller.errorMessage!,
                            style: TextStyle(color: Colors.red),
                          ),
                        ),

                      // Success message
                      if (widget.controller.isSuccess)
                        Padding(
                          padding: const EdgeInsets.only(top: 16),
                          child: Text(
                            "Avaliação enviada com sucesso!",
                            style: TextStyle(color: Colors.green),
                          ),
                        ),

                      SizedBox(height: 30),

                      // Submit button
                      NavableButton(
                        widget.controller.isSubmitting
                            ? "ENVIANDO..."
                            : "ENVIAR AVALIAÇÃO",
                        onPressed: () {
                          widget.controller.submitReview(
                            place: place,
                            reviewText: _reviewController.text,
                            rating: (sliderValue * 5).round(),
                          );

                          if (widget.controller.isSuccess) {
                            ScaffoldMessenger.of(context).showSnackBar(
                              SnackBar(
                                  content:
                                      Text('Avaliação enviada com sucesso!')),
                            );
                            Future.delayed(Duration(seconds: 1), () {
                              Navigator.pop(context);
                            });
                          }
                        },
                      ),
                      SizedBox(height: 20),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
