(ns clj-web-example.localization)

(def dictionary {:title "Hello World!"
                 :not-found "The page you are looking for does not exist."
                 :messages "Messages"
                 :new-message "New message"
                 :sender "Sender"
                 :message "Message"
                 :submit "Submit"})

(defn tr
  [key]
  (get dictionary key))
