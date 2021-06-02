#!groovy

pipeline {
  agent none
  stages {
    stage('Docker Build') {
      steps {
        sh 'docker build .'
      }
    }
  }
}
