job('Ejemplo dos de job DSL') {
  description('Job DSL de ejemplo para el curso de Jenkins')
  scm {
    git('https://github.com/alucard027/jenkinsjob.git', 'main') { node ->
      node / gitConfigName('alucard027')
      node / gitConfigEmail('aldosummers027@gmail.com')
   }  
  }
  parameters {
    stringParam('nombre', defaultValue = 'Aldo', description = 'Parametro de cadena para el job Boolean')
    choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte', 'Jupiter', 'Urano', 'Neptuno'])
    booleanParam('agente', false)
  }
  triggers {
    cron ('H/7 * * * *')
    githubPush()
  }
  steps {
    shell("bash jobscript.sh")
  }
  publishers{
    mailer('alucard027@hotmail.com', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
