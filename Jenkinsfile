// Install gcloud app-engine-java component on build node
// sudo apt-get install google-cloud-sdk-app-engine-java
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
    stage('Test') {
      steps {
        sh './mvnw test'
        // gather the JUnit and Jacoco reports
        junit 'target/surefire-reports/**/*.xml'
        jacoco classPattern: 'target/classes', execPattern: 'target/**.exec'
        findBugs pattern: '**/spotbugsXml.xml'
      }
    }
    stage('Build') {
      steps {
        sh './mvnw clean package -Dmaven.test.skip=true'
      }
    }
    stage('Deploy') {
      steps {
        sh './mvnw appengine:deploy -Dmaven.test.skip=true'
      }
    }
  }

  environment {
    GOOGLE_PROJECT_ID = 'mccp-dev-test'
    GOOGLE_SERVICE_ACCOUNT_KEY = credentials('mccp-dev-test-jenkins');
  }
}

