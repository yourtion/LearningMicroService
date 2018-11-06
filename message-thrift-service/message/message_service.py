import smtplib
from email.header import Header
from email.mime.text import MIMEText

from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
from thrift.transport import TSocket
from thrift.transport import TTransport

from message.api import MessageService

sender = 'imoocd@163.com'
authCode = 'aA111111'


class MessageServiceHandler:

    def sendMobileMessage(self, mobile, message):
        print("sendMobileMessage mobile: %s message: `%s`" % (mobile, message))
        return True

    def sendEmailMessage(self, email, message):
        print("sendEmailMessage email: %s message: %s" % (email, message))
        messageObj = MIMEText(message, "plain", "utf-8")
        messageObj["From"] = sender
        messageObj["To"] = email
        messageObj["Subject"] = Header('慕课网邮件', 'utf-8')

        try:
            smtpObj = smtplib.SMTP("smtp.163.com")
            smtpObj.login(sender, authCode)
            smtpObj.sendmail(sender, [email], messageObj.as_string())
            print("send mail success")
            return True
        except smtplib.SMTPException as ex:
            print("send mail failed!")
            print(ex)
            return False


if __name__ == '__main__':
    port = 9090
    handler = MessageServiceHandler()
    processor = MessageService.Processor(handler)
    transport = TSocket.TServerSocket(None, port)
    tfactory = TTransport.TFramedTransportFactory()
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)
    print("python thrift server start at :%d" % port)
    server.serve()
    print("python thrift server exit")
