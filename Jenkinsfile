# Install gcloud app-engine-java component
# sudo apt-get install google-cloud-sdk-app-engine-java
pipeline {
  agent any
  stages {
    stage('Init') {
      steps {
        echo "Credentails:${GOOGLE_SERVICE_ACCOUNT_KEY}";
        sh '''
          gcloud auth activate-service-account --key-file ${GOOGLE_SERVICE_ACCOUNT_KEY};
          gcloud config set project ${GOOGLE_PROJECT_ID};
        '''
      }
    }
    stage('Build') {
      steps {
        sh './mvnw -q clean package'
      }
    }
    stage('Deploy') {
      steps {
        sh './mvnw appengine:deploy'
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
    GOOGLE_SERVICE_ACCOUNT_KEY = credentials('mccp-dev-test-jenkins');
  }
}
