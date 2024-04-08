package group9.sfursmeetingapplication.models;

import group9.sfursmeetingapplication.repositories.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import group9.sfursmeetingapplication.services.*;

import java.util.*;

public class PeriodicTask {
    private PollRepository pollRepository; 
    private UserServiceImplementation userServiceImplementation; 
    private UserRepository userRepository; 
    private List<Poll> PollsList; 

    public PeriodicTask() {
        // Create a scheduled executor service with a single thread
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        /* 
        // Schedule the task to run every few hours 
        int hoursInterval = 12;
        executor.scheduleAtFixedRate(() -> {
            PollsList = pollRepository.findDistinctPID();
            checkVotingPercentage(PollsList);
        }, 0, hoursInterval, TimeUnit.HOURS);

        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executor.shutdown();
            try {
                executor.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                // Handle interruption
            }
        }));
        */
    }

    private void checkVotingPercentage(List<Poll> pollsList) {
        for(int i = pollsList.size() - 1; i >=0; i--) {

            List<Integer> InvitedUsersList = pollRepository.findInvitedByPID(pollsList.get(i).getPid());
            List<Integer> UserResponsesList = pollRepository.findResponseByPID(pollsList.get(i).getPid());
    
            int userResponsesSizeInt = UserResponsesList.size();
            Double userResponsesSize = Double.valueOf(userResponsesSizeInt);
    
            int invitedUsersSizeInt = InvitedUsersList.size();
            Double invitedUsersSize = Double.valueOf(invitedUsersSizeInt);
            
            if(invitedUsersSize/userResponsesSize >= 0.80) {
                List<User> usersList = userRepository.findUserByUID(pollsList.get(i).getCreator_id());
                User foundUser = usersList.get(0);
                userServiceImplementation.sendPollReadyEmail(foundUser);
                pollsList.remove(i);
            }
    
        }

    }

}

