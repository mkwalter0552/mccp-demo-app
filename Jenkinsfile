pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './mvnw -q clean package'
      }
    }
  }
  environment {
    GOOGLE_PROJECT_ID = 'mccp-dev-test'
  }
}