class User {
  final int id;
  final String name;
  final String email;
  final int points;

  User(this.id, this.name, this.email, this.points);

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      json['idUsuario'],
      json['nome'],
      json['email'],
      json['pontos']
    );
  }
}