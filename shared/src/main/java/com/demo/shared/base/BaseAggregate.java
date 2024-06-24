package com.demo.shared.base;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Data
public abstract class BaseAggregate {
    protected String id;
    private int version = -1;

    private final List<BaseEvent> changes = new ArrayList<>(); // Danh sách các sự kiện chưa được xác nhận
    private final Logger logger = Logger.getLogger(BaseAggregate.class.getName());

    public List<BaseEvent> getUncommittedChanges(){
        return this.changes;
    }

    public void markChangesAsCommitted(){
        this.changes.clear();
    }

    public void applyChange(BaseEvent event, Boolean isNewEvent){
        try {
            Method method = getClass().getDeclaredMethod("apply", event.getClass());
            method.setAccessible(true);
            method.invoke(this, event);
        } catch (NoSuchMethodException e){
            logger.log(Level.WARNING, MessageFormat.format("The apply method was not found in the aggregate for {0}", event.getClass().getName()));
        } catch (Exception e){
            logger.log(Level.SEVERE, "Error applying event to aggregate", e);
        } finally {
            if (isNewEvent){
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event){
        applyChange(event, true);
    }

    public void replayEvents(Iterable<BaseEvent> events){       // Lấy ra sự kieện xảy ra trong quá khứ
        events.forEach(event -> applyChange(event, true));
    }

}

