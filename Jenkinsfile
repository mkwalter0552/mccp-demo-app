pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './mvnw -q clean package'
      }
    }
  }
  post {
    always {
      junit 'target/surefire-reports/**/*.xml'
    }
  }

  environment {
    GOOGLE_PROJECT_ID = 'mccp-dev-test'
  }
}