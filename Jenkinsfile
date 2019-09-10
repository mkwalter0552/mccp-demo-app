pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh './mvnw -q clean package'
      }
    }
    stage('Deploy') {
      steps {
        sh './mvnw appengine:deploy -Dapp.deploy.project=mccp-dev-test'
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
