provider "google" {
  project     = "${var.google_project_id}"
  region      = "us-central1"
  zone = "us-central1-a"
}

data "google_project" "project" {}

# give build service account additional permissions
resource "google_project_iam_member" "builder_iam_admin" {
  project = "${var.google_project_id}"
  role    = "roles/resourcemanager.projectIamAdmin"
  member  = "serviceAccount:${data.google_project.project.number}@cloudbuild.gserviceaccount.com"
}

resource "google_project_iam_member" "builder_sql_editor" {
  project = "${var.google_project_id}"
  role    = "roles/cloudsql.editor"
  member  = "serviceAccount:${data.google_project.project.number}@cloudbuild.gserviceaccount.com"
}

resource "google_project_iam_member" "builder_app_engine" {
  project = "${var.google_project_id}"
  role    = "roles/appengine.appAdmin"
  member  = "serviceAccount:${data.google_project.project.number}@cloudbuild.gserviceaccount.com"
}

# serviceusage.googleapis.com needs to be enabled
resource "google_project_services" "enable_apis" {
  project = "${var.google_project_id}"
  services   = [
      "cloudresourcemanager.googleapis.com",
      "serviceusage.googleapis.com", 
      "appengine.googleapis.com", 
      "sqladmin.googleapis.com", 
      "cloudbuild.googleapis.com",
      "containerregistry.googleapis.com",
      "logging.googleapis.com",
      "pubsub.googleapis.com",
      "storage-api.googleapis.com",
      "oslogin.googleapis.com",
      "compute.googleapis.com"
  ]
}

# create a database server running MySQL 5.7
resource "google_sql_database_instance" "mysql" {
  # name = "mysql-5-7"
  database_version = "MYSQL_5_7"

  settings {
    user_labels = {"color":"plaid"}
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
