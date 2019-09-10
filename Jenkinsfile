pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './mvnw package'
      }
    }
  }
  environment {
    GOOGLE_PROJECT_ID = 'mccp-dev-test'
  }
}