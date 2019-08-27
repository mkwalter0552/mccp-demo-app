provider "google" {
  credentials = "${file("~/.config/gcloud/legacy_credentials/emorter@mkwalter.com/adc.json")}"
  project     = "poc-app-mccp"
  region      = "us-central1"
  zone = "us-central1-a"
}

# create a database server running MySQL 5.7
resource "google_sql_database_instance" "mysql" {
  name = "mysql57"
  database_version = "MYSQL_5_7"

  settings {
    # run on a small, shared-core machine for demo purposes
    tier = "db-f1-micro"
  }
}

# create a user in the database server
resource "google_sql_user" "user" {
  name     = "database_user"
  password = "${var.db_user_password}"
  instance = "${google_sql_database_instance.mysql.name}"
}

# create a database
resource "google_sql_database" "database" {
  name      = "movies"
  instance = "${google_sql_database_instance.mysql.name}"
}