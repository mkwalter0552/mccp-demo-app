provider "google" {
  credentials = "${file("~/.config/gcloud/legacy_credentials/emorter@mkwalter.com/adc.json")}"
  project     = "${var.google_project_id}"
  region      = "us-central1"
  zone = "us-central1-a"
}

# store Terraform state in a remote bucket
terraform {
  backend "gcs" {
    bucket  = "poc-app-mccp-tf-state"
    prefix  = "terraform/state"
  }
}

# create a database server running MySQL 5.7
resource "google_sql_database_instance" "mysql" {
  # name = "mysql-5-7"
  database_version = "MYSQL_5_7"

  settings {
    # run on a small, shared-core machine for demo purposes
    tier = "db-f1-micro"
    ip_configuration {
        authorized_networks {
            name ="all-open"
            value = "0.0.0.0/0"
        }
        ipv4_enabled = true
        require_ssl = false
      }
  }
}

# create a user in the database server
resource "google_sql_user" "user" {
  name = "database_user"
  # hosts this user is allowed to connect from, % means everywhere
  host = "%"
  password = "${var.db_user_password}"
  instance = "${google_sql_database_instance.mysql.name}"
}

# create a database
resource "google_sql_database" "database" {
  name      = "movies"
  instance = "${google_sql_database_instance.mysql.name}"
}

# create an App Engine
resource "google_app_engine_application" "app-engine" {
  project     = "${var.google_project_id}"
  location_id = "us-central"
}